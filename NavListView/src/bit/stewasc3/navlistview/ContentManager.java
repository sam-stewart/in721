package bit.stewasc3.navlistview;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class ContentManager
{
	private ArrayList<Content> mContents;
	private static ContentManager sContentManager;
	
	private ContentManager(Context appContext)
	{
		mContents = new ArrayList<Content>();
		
		mContents.add(new Content("Services", R.drawable.rsz_1library));
		mContents.add(new Content("Fun things to do", R.drawable.rsz_monarch));
		mContents.add(new Content("Dining", R.drawable.rsz_customhouse));
		mContents.add(new Content("Shopping", R.drawable.rsz_thieves_alley));
	}

	public static ContentManager get(Context c)
	{
		if (sContentManager == null)
		{
			sContentManager = new ContentManager(c.getApplicationContext());
		}
		return sContentManager;
	}
	
	public ArrayList<Content> getContents()
	{
		return mContents;
	}
	
	public Content getContent(UUID id)
	{
		Content returnContent = null;
		for(Content content :mContents)
		{
			if (id.equals(content.getId()))
				returnContent = content;
		}
		return returnContent;
	}
}
