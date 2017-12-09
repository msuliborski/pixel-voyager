package com.sulient.pixelvoyager.tools;

public interface PlayServices {
    public boolean getSignedInGPGS();
    public void loginGPGS();
    public void submitScoreGPGS(float score, String id);
    public void unlockAchievementGPGS(String achievementId);
    public void incrementAchievementGPGS(String achievementId);
    public void getLeaderboardsGPGS();
    public void getAchievementsGPGS();
}