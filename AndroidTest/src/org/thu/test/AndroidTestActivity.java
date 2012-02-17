package org.thu.test;

import java.io.File;

import org.thu.test.LoginDlg.ILoginListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AndroidTestActivity extends Activity implements ILoginListener {
	/** Called when the activity is first created. */
	public static final String PREFS_NAME = "MyPrefsFile";
	TextView infoText;
	String tag = "tag";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		infoText = (TextView) findViewById(R.id.infoText);

		final LoginDlg dialog = new LoginDlg(this, R.layout.dialog, this);
		Button showBtn = (Button) findViewById(R.id.showDlgBtn);

		if (dialog.getSession() != null)
			onSuccess(dialog.getSession());
		else
			dialog.show();
		showBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.show();
			};
		});		
		File dir = this.getExternalCacheDir();		
		
		ImageFactory.saveBitmapToFileAsync("http://vnexpress.net/Files/Subject/3b/bd/2a/72/ong-Vo-1.jpg", 64, 64, dir, "abc.png", new ImageFactory.IImageFactoryListener() {
			
			@Override
			public void onFinishLoading(File outFile) {
				// TODO Auto-generated method stub
				Log.i(tag, "done path : " + outFile.getAbsolutePath());
			}
			
			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				Log.i(tag, " error ");
			}
		});

	}

	@Override
	public void onSuccess(String session) {
		infoText.setText("session key : " + session);

	}

	@Override
	public void onFailure(int error) {
		infoText.setText("login failure");
	}

}