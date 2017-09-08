package com.mao.cn.learnDevelopProject.wedget.fancyCoverFlow;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;

import java.util.List;

public class ImageAndTextAdapter extends FancyCoverFlowAdapter {

    private Context mContext;
    private List<PicAndTextInfo> mPicAndTextInfos;
    private int currentPositon;

    public ImageAndTextAdapter(Context context, List<PicAndTextInfo> picAndTextInfos) {
        mContext = context;
        mPicAndTextInfos = picAndTextInfos;
    }

    @Override
    public int getCount() {
        return mPicAndTextInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mPicAndTextInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getCoverFlowItem(int position, View reusableView, ViewGroup parent) {

        PicAndTextInfo picAndTextInfo = mPicAndTextInfos.get(position);

        CustomViewGroup customViewGroup;

        if (reusableView != null) {
            customViewGroup = (CustomViewGroup) reusableView;
        } else {
            customViewGroup = new CustomViewGroup(parent.getContext());
            int width = mContext.getResources().getDimensionPixelSize(R.dimen.d100);
            int height = mContext.getResources().getDimensionPixelSize(R.dimen.d100);
            customViewGroup.setLayoutParams(new FancyCoverFlow.LayoutParams(width, height));
        }

        customViewGroup.getImageView().setImageResource(picAndTextInfo.getPicture());
        customViewGroup.getTextView().setText(picAndTextInfo.getTextContent());
        if (currentPositon == position) {
            customViewGroup.getTextView().setVisibility(View.VISIBLE);
        } else {
            customViewGroup.getTextView().setVisibility(View.VISIBLE);
        }
        return customViewGroup;
    }


    public void setCurrentPosition(int position) {
        currentPositon = position;
        notifyDataSetChanged();
    }


    private static class CustomViewGroup extends LinearLayout {


        private TextView tvLoginText;

        private ImageView ivLoginLogo;


        private CustomViewGroup(Context context) {
            super(context);

            this.setOrientation(VERTICAL);
            this.setVerticalGravity(Gravity.CENTER_VERTICAL);

            this.tvLoginText = new TextView(context);
            this.ivLoginLogo = new ImageView(context);


            LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            this.tvLoginText.setLayoutParams(layoutParams);
            this.ivLoginLogo.setLayoutParams(layoutParams);

            this.tvLoginText.setGravity(Gravity.CENTER);
            this.tvLoginText.setVisibility(View.INVISIBLE);
            this.tvLoginText.setTextColor(getResources().getColor(R.color.c_000000));
            this.tvLoginText.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.text_size_11));
            this.tvLoginText.setPadding(0, getResources().getDimensionPixelSize(R.dimen.d6), 0, 0);

            this.ivLoginLogo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            this.ivLoginLogo.setAdjustViewBounds(true);

            this.addView(this.ivLoginLogo);
            this.addView(this.tvLoginText);
        }

        private TextView getTextView() {
            return tvLoginText;
        }

        private ImageView getImageView() {
            return ivLoginLogo;
        }
    }
}
