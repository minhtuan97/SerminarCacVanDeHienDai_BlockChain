package BC_Hospital.Project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import BC_Hospital.Project.DPaaS.DataManagement.Offchain;
import BC_Hospital.Project.DPaaS.DataManagement.Onchain;
import BC_Hospital.Project.Model.BlockOffChain;
import BC_Hospital.Project.Model.BlockOnChain;
import BC_Hospital.Project.repository.BlockOffChainRepository;



@RestController
public class Home {
	
	@Autowired
	private Offchain aOffchain;
	
	@Autowired
	private Onchain aOnchain;
	
	@RequestMapping("/")
    @ResponseBody
    public Optional<BlockOnChain> welcome() {
		BlockOnChain blockOnChain = new BlockOnChain("112233456", "112233455", "11223345603030303 ahi dsoac ", 11111);
		//BlockOffChain  aBlockOffChain = new BlockOffChain("112233456", "03030303 ahi dsoac ".getBytes());
		//return aOffchain.storeOffChainData("1122334567", "03030303 ahi dsoac ".getBytes());
		//return blockOffChainRepository.findByhashfile("112233456");
        //return aOffchain.obtainOffChainData("112233456");
		//return aOffchain.finaAll();
//		aOnchain.storeOnChainData("attribute", "ciphertext", blockOnChain );
//		return"aaa";
		return aOnchain.obtainOnChainData("112233456");
    }

}
