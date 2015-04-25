package bit.stewasc3.webservices2;

public class Artist
{
	private String mName;
	private String mImgUrl;

	public String getName()
	{
		return mName;
	}
	public void setName(String name)
	{
		mName = name;
	}
	public String getImgUrl()
	{
		return mImgUrl;
	}
	public void setImgUrl(String imgUrl)
	{
		mImgUrl = imgUrl;
	}
	public String getListeners()
	{
		return mListeners;
	}
	public void setListeners(String listeners)
	{
		mListeners = listeners;
	}
	private String mListeners;
}
