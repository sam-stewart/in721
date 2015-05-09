package bit.stewasc3.retrofittest.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by samuel on 8/05/15.
 */
public class Post
{
    private int userId;
    private int id;
    private String title;
    private String body;

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
