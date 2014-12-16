package com.itmanapp2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BaseActivity extends Activity {
	
	protected void setPhone() {
		TextView phone = (TextView) findViewById(R.id.phoneTv);
		if (phone == null) {
			return;
		}
		phone.setText("400-647-1600");
		phone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent9 = new Intent(Intent.ACTION_CALL,Uri.parse("tel:400-647-1600"));
				startActivity(intent9);
			}
		});
	}
}
