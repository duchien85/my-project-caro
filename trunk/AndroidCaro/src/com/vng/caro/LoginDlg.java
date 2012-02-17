package com.vng.caro;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gsn.caro.settings.CaroSettings;
import com.gsn.engine.mercurry.ZingMe;

public class LoginDlg extends Dialog {
	public static final String PREFS_NAME = CaroSettings.PREFS_NAME;
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String SAVE_INFO = "save_info";
	public static final String SESSION = "session";

	public interface ILoginListener {
		void onSuccess(String session);

		void onFailure(int error);
	}

	public ILoginListener listener;
	public String user, pass, session;
	public boolean saveinfo;
	SharedPreferences settings;
	SharedPreferences.Editor editor;

	public String getSession() {
		return session;
	}

	public LoginDlg(final Context context, int layout, final ILoginListener listener) {
		super(context);
		this.listener = listener;
		this.setContentView(layout);
		this.setTitle("Login");

		final EditText userText = (EditText) this.findViewById(R.id.userText);
		final EditText passText = (EditText) this.findViewById(R.id.passText);
		final CheckBox saveInfoCheckBox = (CheckBox) this.findViewById(R.id.saveInfoCheckBox);
		settings = context.getSharedPreferences(PREFS_NAME, 0);
		user = settings.getString(USERNAME, "username");
		editor = settings.edit();
		
		pass = settings.getString(PASSWORD, "password");
		session = settings.getString(SESSION, null);
		saveinfo = settings.getBoolean(SAVE_INFO, false);		
		userText.setText(user);
		passText.setText(pass);
		saveInfoCheckBox.setChecked(saveinfo);

		final Button loginBtn = (Button) this.findViewById(R.id.loginBtn);
		loginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				saveinfo = saveInfoCheckBox.isChecked();
				editor.putBoolean(SAVE_INFO, saveinfo);
				if (!saveinfo){
					editor.remove(SAVE_INFO);
					editor.remove(USERNAME);
					editor.remove(PASSWORD);
					editor.remove(SESSION);
				}
				
				editor.commit();
				
				
				user = userText.getText().toString();
				pass = passText.getText().toString();								
				Log.i("login", "user: " + user);
				Log.i("login", "pass: " + pass);

				session = ZingMe.getSessionkeyUser(user, pass);
				Log.i("login", "session: " + session);

				LoginDlg.this.hide();
				if (session != null) {
					listener.onSuccess(session);
					if (saveinfo) {
						editor.putString(USERNAME, user);
						editor.putString(PASSWORD, pass);
						editor.putString(SESSION, session);						
						editor.commit();
					}
				} else
					listener.onFailure(-1);
			}
		});
	}

	public String retryLogin() {
		Log.i("login", "retry login : " + user + " " + pass);
		String session = ZingMe.getSessionkeyUser(user, pass);
		if (session != null){
			editor.putString(SESSION, session);						
			editor.commit();
		}
		return session;
	}
}
