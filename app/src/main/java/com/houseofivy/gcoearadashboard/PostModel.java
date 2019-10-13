package com.houseofivy.gcoearadashboard;

public class PostModel {

    private String id;
    private String userName;
    private String userPost;
    private String postDescription;

    public PostModel(String id, String userName, String userPost, String postDescription) {
        this.id = id;
        this.userName = userName;
        this.userPost = userPost;
        this.postDescription = postDescription;
    }

    public PostModel() {
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPost() {
        return userPost;
    }

    public String getPostDescription() {
        return postDescription;
    }

}
