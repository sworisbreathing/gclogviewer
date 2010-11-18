/**
 * High-Speed Service Framework (HSF)
 * 
 * www.taobao.com
 * 	(C) �Ա�(�й�) 2003-2008
 */
package code.google.gclogviewer.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import code.google.gclogviewer.OneLineGCData;

/**
 * ������
 *
 * @author <a href="mailto:bixuan@taobao.com">bixuan</a>
 */
public class CommonLogParser {

	public OneLineGCData parse(String lineInfo,Pattern pattern1,Pattern pattern2) throws Exception {
		OneLineGCData data=new OneLineGCData();
		boolean result=formData(lineInfo, data, pattern1);
		if(!result){
			result=formData(lineInfo, data, pattern2);
			if(!result){
				System.err.println("parse line failed: "+lineInfo);
			}
		}
		return data;
	}
	
	private boolean formData(String lineInfo,OneLineGCData data,Pattern pattern){
		boolean result=false;
		Matcher match = pattern.matcher(lineInfo);
		String[] memoryChanges=new String[2];
		if (match.find()) {
			if(match.groupCount() == 4){
				data.setOccurTime(match.group(1));
				memoryChanges[0]=match.group(2);
				memoryChanges[1]=match.group(3);
				data.setMemoryChangeInfo(memoryChanges);
				data.setPauseTime(match.group(4));
			}
			else if(match.groupCount() == 5){
				data.setOccurDateTime(match.group(1));
				data.setOccurTime(match.group(2));
				memoryChanges[0]=match.group(3);
				memoryChanges[1]=match.group(4);
				data.setMemoryChangeInfo(memoryChanges);
				data.setPauseTime(match.group(5));
			}
			result=true;
        }
		return result;
	}
	
}
