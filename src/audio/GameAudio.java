package audio;

import audio.tts.TextToSpeech;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class GameAudio
{
    private final static String COMBAT_AUDIO_FILE = "src/audio/sounds/combat.wav";
    private final static String CONFLICT_AUDIO_FILE = "src/audio/sounds/conflict.wav";
    private final static String TEAM_1_WON = "src/audio/sounds/team1.wav";
    private final static String TEAM_2_WON = "src/audio/sounds/team2.wav";
    private final static String TEAM_3_WON = "src/audio/sounds/team3.wav";
    private final static String TEAM_4_WON = "src/audio/sounds/team4.wav";
    private final static String TEAM_5_WON = "src/audio/sounds/team5.wav";
    private final static String THEME = "src/audio/sounds/theme.wav";

    private static Clip mCombatClip;
    private static Clip mConflictClip;
    private static Clip mTeam1Won;
    private static Clip mTeam2Won;
    private static Clip mTeam3Won;
    private static Clip mTeam4Won;
    private static Clip mTeam5Won;
    private static Clip mTheme;
    private static TextToSpeech mTextToSpeach;

    public static void initializeTTS()
    {
        mTextToSpeach = new TextToSpeech();
    }

    public static void initialize()
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(GameAudio.COMBAT_AUDIO_FILE));
            GameAudio.mCombatClip = AudioSystem.getClip();
            GameAudio.mCombatClip.open(audioInputStream);
            GameAudio.mCombatClip.loop(Clip.LOOP_CONTINUOUSLY);
            GameAudio.mCombatClip.stop();
            audioInputStream = AudioSystem.getAudioInputStream(new File(GameAudio.CONFLICT_AUDIO_FILE));
            GameAudio.mConflictClip = AudioSystem.getClip();
            GameAudio.mConflictClip.open(audioInputStream);
            GameAudio.mConflictClip.stop();
            audioInputStream = AudioSystem.getAudioInputStream(new File(GameAudio.TEAM_1_WON));
            GameAudio.mTeam1Won = AudioSystem.getClip();
            GameAudio.mTeam1Won.open(audioInputStream);
            GameAudio.mTeam1Won.stop();
            audioInputStream = AudioSystem.getAudioInputStream(new File(GameAudio.TEAM_2_WON));
            GameAudio.mTeam2Won = AudioSystem.getClip();
            GameAudio.mTeam2Won.open(audioInputStream);
            GameAudio.mTeam2Won.stop();
            audioInputStream = AudioSystem.getAudioInputStream(new File(GameAudio.TEAM_3_WON));
            GameAudio.mTeam3Won = AudioSystem.getClip();
            GameAudio.mTeam3Won.open(audioInputStream);
            GameAudio.mTeam3Won.stop();
            audioInputStream = AudioSystem.getAudioInputStream(new File(GameAudio.TEAM_4_WON));
            GameAudio.mTeam4Won = AudioSystem.getClip();
            GameAudio.mTeam4Won.open(audioInputStream);
            GameAudio.mTeam4Won.stop();
            audioInputStream = AudioSystem.getAudioInputStream(new File(GameAudio.TEAM_5_WON));
            GameAudio.mTeam5Won = AudioSystem.getClip();
            GameAudio.mTeam5Won.open(audioInputStream);
            GameAudio.mTeam5Won.stop();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void playConflictSound()
    {
        mConflictClip.start();
    }

    public static void playCombatSound()
    {
        mCombatClip.start();
    }

    public static void stopCombatSound()
    {
        mCombatClip.stop();
    }

    public static void playTeam1WonSound()
    {
        mTeam1Won.start();
    }

    public static void playTeam2WonSound()
    {
        mTeam2Won.start();
    }

    public static void playTeam3WonSound()
    {
        mTeam3Won.start();
    }

    public static void playTeam4WonSound()
    {
        mTeam4Won.start();
    }

    public static void playTeam5WonSound()
    {
        mTeam5Won.start();
    }

    public static void sayTTS(String s)
    {
        mTextToSpeach.speak(s,2.0f,false,true);
    }

    public static void startGameTheme()
    {

        AudioInputStream audioInputStream;
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(new File(GameAudio.THEME));
            GameAudio.mTheme = AudioSystem.getClip();
            GameAudio.mTheme.open(audioInputStream);
            FloatControl volume = (FloatControl) GameAudio.mTheme.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-15.0f);
            GameAudio.mTheme.loop(Clip.LOOP_CONTINUOUSLY);
            GameAudio.mTheme.start();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
