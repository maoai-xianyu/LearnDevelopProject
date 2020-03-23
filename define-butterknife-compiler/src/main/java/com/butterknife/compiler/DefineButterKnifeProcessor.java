package com.butterknife.compiler;


import com.butterknife.annontations.DefineBindView;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @author zhangkun
 * @time 2020-03-22 22:47
 */
@AutoService(Processor.class)
public class DefineButterKnifeProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
    }

    // 1. 指定处理的版本

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    // 给到需要处理的注解
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : getSupportedAnnotations()) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        // 需要解析的
        annotations.add(DefineBindView.class);
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // process 方法代表的是：有注解就都会进来，但是这里里面是一团乱麻
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(DefineBindView.class);
        // 解析 属性 activity -> List<Element>
        Map<Element, List<Element>> elementMap = new LinkedHashMap<>();
        for (Element element : elements) {
            Element enclosedElement = element.getEnclosingElement();
            System.out.println("------process------------>" + element.getSimpleName().toString() + " " + enclosedElement.getSimpleName().toString());
            List<Element> viewBindElements = elementMap.get(enclosedElement);
            if (viewBindElements == null) {
                viewBindElements = new ArrayList<>();
                elementMap.put(enclosedElement, viewBindElements);
            }
            viewBindElements.add(element);
        }

        // 生成代码
        Set<Map.Entry<Element, List<Element>>> entries = elementMap.entrySet();
        for (Map.Entry<Element, List<Element>> entry : entries) {
            Element enclosedElement = entry.getKey();
            List<Element> viewBindElements = entry.getValue();

            // 生成 public class DefineButterKnifeActivity_ViewBinding implements Unbinder
            ClassName unbinderClassName = ClassName.get("com.butterknife", "DefineUnbinder");
            String activityClassNameStr = enclosedElement.getSimpleName().toString();
            ClassName activityClassName = ClassName.bestGuess(activityClassNameStr);
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(activityClassNameStr + "_ViewBinding")
                    .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                    .addSuperinterface(unbinderClassName)
                    .addField(activityClassName, "target", Modifier.PRIVATE);

            // 实现unbind方法
            ClassName callSuper = ClassName.get("androidx.annotation", "CallSuper");
            MethodSpec.Builder unbindMethodBuilder = MethodSpec.methodBuilder("unbind")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(callSuper);

            unbindMethodBuilder.addStatement("$T target = this.target", activityClassName);
            unbindMethodBuilder.addStatement("if (target == null) throw new IllegalStateException(\"Bindings already cleared.\")");
            unbindMethodBuilder.addStatement("this.target = null");

            // 构造函数 一个参数
            ClassName uiThread = ClassName.get("androidx.annotation", "UiThread");
            MethodSpec.Builder constructorMethodBuilder = MethodSpec.constructorBuilder();

            constructorMethodBuilder.addParameter(activityClassName, "target")
                    .addAnnotation(uiThread)
                    .addModifiers(Modifier.PUBLIC);

            constructorMethodBuilder.addStatement("this(target, target.getWindow().getDecorView())");

            classBuilder.addMethod(constructorMethodBuilder.build());


            // 构造函数 二个参数
            ClassName viewName = ClassName.get("android.view", "View");
            MethodSpec.Builder constructorMethod2Builder = MethodSpec.constructorBuilder();

            constructorMethod2Builder.addParameter(activityClassName, "target")
                    .addParameter(viewName, "source")
                    .addAnnotation(uiThread)
                    .addModifiers(Modifier.PUBLIC);


            constructorMethod2Builder.addStatement("this.target = target;");
            // findViewById
            for (Element viewBindElement : viewBindElements) {
                // target.tvHeaderTitle = Utils.findRequiredViewAsType(source, R.id.tv_header_title, "field 'tvHeaderTitle'", TextView.class);
                // target.tvHeaderTitle = Utils.findViewById(source, R.id.tv_header_title);
                String fieldName = viewBindElement.getSimpleName().toString();
                ClassName utilsClassName = ClassName.get("com.butterknife", "Utils");
                int resId = viewBindElement.getAnnotation(DefineBindView.class).value();
                constructorMethod2Builder.addStatement("target.$L = $T.findViewById(target, $L)", fieldName, utilsClassName, resId);

                unbindMethodBuilder.addStatement("target.$L = null", fieldName);

            }

            classBuilder.addMethod(unbindMethodBuilder.build());

            classBuilder.addMethod(constructorMethod2Builder.build());


            // 生成类，看下效果哟
            try {
                String packageName = mElementUtils.getPackageOf(enclosedElement).getQualifiedName().toString();
                JavaFile.builder(packageName, classBuilder.build())
                        .addFileComment("define butterknife 自动生成")

                        .build().writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("翻车");
            }

        }


        return false;
    }
}
