package com.mao.cn.learnDevelopProject.ui.activity.retrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author zhangkun
 * @time 2020-02-08 15:56
 */
public interface GitHubService {

    // https://api.github.com/users/octocat/repos
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
