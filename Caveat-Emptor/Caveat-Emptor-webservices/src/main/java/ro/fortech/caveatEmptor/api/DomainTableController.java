package ro.fortech.caveatEmptor.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.fortech.caveatEmptor.business.services.DomainTableService;

@Controller
@RequestMapping("/ws")
public class DomainTableController {

	@Autowired
	private DomainTableService domainTableService;

	@RequestMapping(value = "/domainTable", method = RequestMethod.GET)
	public @ResponseBody Map<String, List<String>> geDomainTables() {
		return new HashMap<>();
	}

	@RequestMapping(value = "/authorName", method = RequestMethod.GET)
	public @ResponseBody String geAuthorName() {
		return domainTableService.getName();
	}

}
