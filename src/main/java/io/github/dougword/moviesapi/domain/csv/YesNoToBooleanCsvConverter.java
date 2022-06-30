package io.github.dougword.moviesapi.domain.csv;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class YesNoToBooleanCsvConverter<T, I> extends AbstractBeanField<T, I> {

	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		if (value == null)
			return false;
		return value.trim().toLowerCase().equals("yes");
	}

}
