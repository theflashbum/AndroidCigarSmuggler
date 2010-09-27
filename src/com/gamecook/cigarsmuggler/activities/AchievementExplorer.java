package com.gamecook.cigarsmuggler.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.gamecook.cigarsmuggler.R;
import com.openfeint.api.OpenFeint;
import com.openfeint.api.resource.Achievement;

import java.util.List;

public class AchievementExplorer extends ListActivity {
	
	public class AchievementAdapter {
		public Achievement mAchievement;
		public AchievementAdapter(Achievement achievement) {
			mAchievement = achievement;
		}
		public String toString() {
			if (mAchievement.isSecret && !mAchievement.isUnlocked) {
				return "(Secret)";
			}
			return mAchievement.title + (mAchievement.isUnlocked ? " (U)" : " (L)");
		}
	}
	
    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if (resultCode != Activity.RESULT_CANCELED) {
    		downloadAchievements();
    	}
    }
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        downloadAchievements();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	OpenFeint.setCurrentActivity(this);
    }

    private void downloadAchievements() {
        setListAdapter(new ArrayAdapter<String>(this, R.layout.main_menu_item, new String[] { "Loading..." }));
        
        Achievement.list(new Achievement.ListCB() {
			@Override
			public void onSuccess(final List<Achievement> achievements) {
				if (null == achievements || achievements.size() == 0) {
			        setListAdapter(new ArrayAdapter<String>(AchievementExplorer.this, R.layout.main_menu_item, new String[] { "No Achievements." }));
			        return;
				}
				
				AchievementAdapter adapted[] = new AchievementAdapter[achievements.size()];
				for(int i=0; i<achievements.size(); ++i) {
					adapted[i] = new AchievementAdapter(achievements.get(i));
				}
		        setListAdapter(new ArrayAdapter<AchievementAdapter>(AchievementExplorer.this, R.layout.main_menu_item, adapted));
		        
		        ListView lv = getListView();
		        lv.setOnItemClickListener(new OnItemClickListener() {
			        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			        	Achievement a = achievements.get(position);
			        	Intent i = new Intent(AchievementExplorer.this, AchievementInvestigator.class);
			        	i.putExtra("achievement_id", a.resourceID());
			        	startActivityForResult(i, 0);
			        }
			    });
			}

			@Override public void onFailure(String exceptionMessage) {
		        setListAdapter(new ArrayAdapter<String>(AchievementExplorer.this, R.layout.main_menu_item, new String[] { "Error (" + exceptionMessage + ")" }));
			}
        });
    }
}
