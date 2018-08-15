package com.sulient.pixelvoyager.tools;

public class DesktopGoogleServices implements PlayServices
{

    @Override
    public boolean getSignedInGPGS() {
        return false;
    }

    @Override
    public void loginGPGS() {

        System.out.print("loginGPGS");

    }

    @Override
    public void submitScoreGPGS(float score, String id) {

        System.out.print("submitScoreGPGS");

    }

    @Override
    public void unlockAchievementGPGS(String achievementId) {

        System.out.print("unlockAchievementGPGS");

    }

    @Override
    public void incrementAchievementGPGS(String achievementId) {

        System.out.print("incrementAchievementGPGS");
    }

    @Override
    public void getLeaderboardsGPGS() {

        System.out.print("getLeaderboardGPGS");

    }

    @Override
    public void getAchievementsGPGS() {

        System.out.print("getAchievementsGPGS");

    }
}

