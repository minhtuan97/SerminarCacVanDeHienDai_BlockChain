package BC_Hospital.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import BC_Hospital.Project.repository.OffchainRepository;

@RestController
public class Home {
	
//	@Autowired
//	OffchainRepository offchainRepository;
	
	@RequestMapping("/")
    @ResponseBody
    public String welcome() {
        //return offchainRepository.getFileFromHash("h111").toString();
		return "bllllllllll";
    }

}
