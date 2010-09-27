package com.gamecook.cigarsmuggler.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.gamecook.cigarsmuggler.R;
import com.openfeint.api.OpenFeint;
import com.openfeint.api.resource.Leaderboard;
import com.openfeint.api.resource.Score;

public class ScorePoster extends Activity {
	String mLeaderboardID;
	
    @Override
    public void onResume() {
    	super.onResume();
    	OpenFeint.setCurrentActivity(this);
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mLeaderboardID = this.getIntent().getExtras().getString("leaderboard_id");

        setContentView(R.layout.score_poster);
        
        Button b = (Button)findViewById(R.id.post_score_button);
        b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TextView scoreTV = (TextView)ScorePoster.this.findViewById(R.id.post_score_textview);
				TextView textTV = (TextView)ScorePoster.this.findViewById(R.id.display_text_textview);
				long scoreValue;
				
				try {
  				scoreValue = new Long(scoreTV.getText().toString()).longValue();				  
				} catch(NumberFormatException e) {
          Toast.makeText(ScorePoster.this, "Please enter the score as a number", Toast.LENGTH_SHORT).show();
    			return;
				}
				
				
				String textValue = textTV.getText().toString();
				Score s = new Score(scoreValue, (textValue.length() > 0 ? textValue : null));
				Leaderboard l = new Leaderboard(mLeaderboardID);
				s.submitTo(l, new Score.SubmitToCB() {
					
					@Override
					public void onSuccess(boolean newHighScore) {
						// sweet, pop the thingerydingery
						ScorePoster.this.setResult(Activity.RESULT_OK);
						ScorePoster.this.finish();
					}
					
					@Override public void onFailure(String exceptionMessage) {
						Toast.makeText(ScorePoster.this, "Error (" + exceptionMessage + ") posting score.", Toast.LENGTH_SHORT).show();
						ScorePoster.this.setResult(Activity.RESULT_CANCELED);
						ScorePoster.this.finish();
					}
				});
			}
        	
        });
    }

}
