
package dev.briefcase.library.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dev.briefcase.library.config.exception.GeneralServiceException;
import dev.briefcase.library.config.exception.ValidateFieldsException;

public class Tool {

	public static String dateToShow(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String outPut = (date == null) ? null : simpleDateFormat.format(date);
		return outPut ;
	}

	public static Date dateToSave(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		
		try {
			Date outPut = (date == null) ? null : simpleDateFormat.parse(date);
			return  outPut;

		} catch (ParseException e) {
			throw new ValidateFieldsException("Incorrect date format.");

		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	public static String removeWhiteSpace(String string) {
		String outPut = (string == null) ? null : string.trim();
		return outPut;
	}

}
