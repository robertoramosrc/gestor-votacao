package br.com.coopera.gestorvotacao.infra.repository.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtils {

	private DateUtils() {
	}

	public static java.sql.Date toSQLDate(LocalDate date) {
		return (null == date) ? null : java.sql.Date.valueOf(date);
	}

	public static Timestamp toSQLTimestamp(LocalDateTime date) {
		return (null == date) ? null : Timestamp.valueOf(date);
	}

	public static java.util.Date toDate(LocalDate localDate) {
		return localDate == null ? null : java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static LocalDate toLocalDate(java.sql.Date date) {
		return date == null ? null : date.toLocalDate();
	}
}
