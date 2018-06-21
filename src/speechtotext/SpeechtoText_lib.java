package speechtotext;

import java.io.File;
import java.io.FileNotFoundException;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;

public class SpeechtoText_lib {

	SpeechToText service;
    RecognizeOptions options=null;

    public SpeechtoText_lib(File audio){
    	service = new SpeechToText();
	    service.setUsernameAndPassword("j16013", "j16013");
	    try {
	    	options = new RecognizeOptions.Builder()
				.model("ja-JP_BroadbandModel")
				.audio(audio)
				.contentType(RecognizeOptions.ContentType.AUDIO_WAV)
				.build();
	    } catch (FileNotFoundException e) {
	    	// TODO 自動生成された catch ブロック
	    	e.printStackTrace();
	    }

    }
    public SpeechRecognitionResults getTranscript() {
		SpeechRecognitionResults transcript = service.recognize(options).execute();
		return transcript;
    }
}
