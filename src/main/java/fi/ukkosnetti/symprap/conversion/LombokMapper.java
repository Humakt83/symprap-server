package fi.ukkosnetti.symprap.conversion;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xebia.jacksonlombok.JacksonLombokAnnotationIntrospector;

@SuppressWarnings("serial")
@Component
public class LombokMapper extends ObjectMapper {

	private LombokMapper() {
		super();
		this.setAnnotationIntrospector(new JacksonLombokAnnotationIntrospector());
	}
}
