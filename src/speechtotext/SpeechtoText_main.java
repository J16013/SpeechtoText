package speechtotext;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;

public class SpeechtoText_main {


		public static void main(String[] args) {

		    MySQL mysql = new MySQL();

		    //ファイル読み込み
		    SpeechtoText_lib slib = new SpeechtoText_lib(new File("./audio/output.wav"));
		    //結果取得
		    SpeechRecognitionResults transcript = slib.getTranscript();
		    //結果表示
		    System.out.println(transcript);

		    JsonNode node = null;
		    String s = String.valueOf(transcript);
		    ObjectMapper mapper = new ObjectMapper();
		    try{
		    	node = mapper.readTree(s);
		    	for(int i = 0; i < node.get("results").size(); i++){
		    		String text = node.get("results").get(i).get("alternatives").get(0).get("transcript").toString();
		    		double confidence = node.get("results").get(i).get("alternatives").get(0).get("confidence").asDouble();

		    		//出力
		    		System.out.println("transcript:"+text);
		 			System.out.println("confidence:"+confidence);
			    	mysql.updateImage(text, confidence);
		    	}
		    }catch(IOException e){
		    	e.printStackTrace();
		    }

		    String Transcript = node.get("results").get(0).get("alternatives").get(0).get("transcript").asText();
		    double Confidence = node.get("results").get(0).get("alternatives").get(0).get("confidence").asDouble();

		    //出力
		    System.out.println("transcript:"+Transcript);
			System.out.println("confidence:"+Confidence);


		  }
	}


