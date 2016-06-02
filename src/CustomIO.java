/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class CustomIO {
	
	public int language = 1;
	
	private static final CustomIO INSTANCE = new CustomIO();
    
    String voiceName = "kevin16";
    
    VoiceManager voiceManager = VoiceManager.getInstance();
    Voice voice = voiceManager.getVoice(voiceName);
	
	public CustomIO() {
		super();
		if (INSTANCE != null) {
			throw new IllegalStateException("Already instantiated");
		} else {
		    voice.allocate();
		}
	}
	
	public static CustomIO getInstance() {
        return INSTANCE;
    }
	
	public void println(String text)
	{
		if(language == 1){
			System.out.println(text);
		} else if(language == 2)
		{
		    voice.speak(text);
		}
	}	
	
	public String input(int language)
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if(language == 1){
			try {
				return br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(language == 2)
		{
			try {
				return br.readLine();
				/******/
				// return voice text transcription
				/******/
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}
}
