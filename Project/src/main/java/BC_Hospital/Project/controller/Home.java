package BC_Hospital.Project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import BC_Hospital.Project.DPaaS.DataManagement.Offchain;
import BC_Hospital.Project.Model.BlockOffChain;
import BC_Hospital.Project.repository.BlockOffChainRepository;



@RestController
public class Home {
	@Autowired
	private BlockOffChainRepository blockOffChainRepository;
	private Offchain aOffchain;
	@RequestMapping("/")
    @ResponseBody
    public Optional<BlockOffChain> welcome() {
		//BlockOffChain  aBlockOffChain = new BlockOffChain("112233456", "03030303 ahi dsoac ".getBytes());
		//return blockOffChianRepository.save(aBlockOffChain);
		return blockOffChainRepository.findByhashfile("112233456");
        //return aOffchain.obtainOffChainData("112233456");
    }

}
