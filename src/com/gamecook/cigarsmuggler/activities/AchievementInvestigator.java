package com.gamecook.cigarsmuggler.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.gamecook.cigarsmuggler.R;
import com.openfeint.api.OpenFeint;
import com.openfeint.api.resource.Achievement;

public class AchievementInvestigator extends Activity {
	String mAchievementID;
	
    @Override
    public void onResume() {
    	super.onResume();
    	OpenFeint.setCurrentActivity(this);
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.achievement_investigator);
        
        Bundle extras = this.getIntent().getExtras();
    	mAchievementID = extras.getString("achievement_id");

    	final TextView titleLabel = ((TextView)findViewById(R.id.achievement_title));
    	final TextView descriptionLabel = ((TextView)findViewById(R.id.achievement_description));
    	final TextView statusLabel = ((TextView)findViewById(R.id.achievement_status));
    	final Button unlock = (Button)findViewById(R.id.achievement_unlock);

		titleLabel.setText("Loading...");
		descriptionLabel.setText("Loading...");
		statusLabel.setText("Loading...");
		unlock.setEnabled(false);

    	final Achievement a = new Achievement(mAchievementID);
    	a.load(new Achievement.LoadCB() {
			@Override public void onSuccess() {
		    	titleLabel.setText(a.title);
		    	descriptionLabel.setText(a.description);
		    	statusLabel.setText(a.isUnlocked ? "unlocked" : "locked");

		    	if (!a.isUnlocked) {
		    		unlock.setEnabled(true);
		            unlock.setOnClickListener(new OnClickListener() {
		    			@Override
		    			public void onClick(View v) {
		    				new Achievement(mAchievementID).unlock(new Achievement.UnlockCB () {
		    					@Override
		    					public void onSuccess(boolean newUnlock) {
		    						AchievementInvestigator.this.setResult(Activity.RESULT_OK);
		    						AchievementInvestigator.this.finish();
		    					}
		    					@Override public void onFailure(String exceptionMessage) {
		    						Toast.makeText(AchievementInvestigator.this, "Error (" + exceptionMessage + ") unlocking achievement.", Toast.LENGTH_SHORT).show();
		    						AchievementInvestigator.this.setResult(Activity.RESULT_CANCELED);
		    						AchievementInvestigator.this.finish();
		    					}
		    				});
		    			}
		            });
		        }
		    	
		    	a.downloadIcon(new Achievement.DownloadIconCB() {
					@Override public void onSuccess(Bitmap iconBitmap) {
						((ImageView)findViewById(R.id.achievement_icon)).setImageBitmap(iconBitmap);
					}
				});
			}
    	});
    }
}
