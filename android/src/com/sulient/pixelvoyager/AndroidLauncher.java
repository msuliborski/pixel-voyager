package com.sulient.pixelvoyager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.sulient.pixelvoyager.tools.PlayServices;

public class AndroidLauncher extends AndroidApplication implements PlayServices, GameHelper.GameHelperListener {

	private GameHelper gameHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (gameHelper == null) {
			gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
			gameHelper.enableDebugLog(true);
		}

		gameHelper.setMaxAutoSignInAttempts(10);
		gameHelper.setup(this);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.hideStatusBar = true;
		config.useImmersiveMode = true;
		initialize(new Main(this), config);
	}

	@Override
	public boolean getSignedInGPGS() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void loginGPGS() {
		if (!gameHelper.isSignedIn()) {
			try {runOnUiThread(new Runnable() {
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});} catch (final Exception ex) {
				Gdx.app.log("MainActivity", "Log in failed: " + ex.getMessage() + ".");
			}
		} else {
			gameHelper.reconnectClient();
		}
	}

	@Override
	public void submitScoreGPGS(float score, String scoreId) {
		if (gameHelper.isSignedIn()) {Games.Leaderboards.submitScore(gameHelper.getApiClient(), scoreId, (int) (score*1000));}
	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		if (gameHelper.isSignedIn()) {Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);}
	}

	@Override
	public void incrementAchievementGPGS(String achievementId) {
		if (gameHelper.isSignedIn()) {Games.Achievements.increment(gameHelper.getApiClient(), achievementId, 1);}
	}

	@Override
	public void getLeaderboardsGPGS() {
		if (gameHelper.isSignedIn()) {startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(gameHelper.getApiClient()), 100);}
		else if (!gameHelper.isConnecting()) {loginGPGS();}
	}

	@Override
	public void getAchievementsGPGS() {
		if (gameHelper.isSignedIn()) {startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 101);}
		else if (!gameHelper.isConnecting()) {loginGPGS();}
	}

	@Override
	public void onSignInFailed() {
		gameHelper.getSignInError();
	}

	@Override
	public void onSignInSucceeded() {

	}
}