package BC_Hospital.Project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import BC_Hospital.Project.Model.BlockOffChain;

@Repository
public interface BlockOffChainRepository extends CrudRepository<BlockOffChain, String>{
	
	// Lấy một record off chain thông qua mã hash của nó
	BlockOffChain findByHash(String hash);
	
//	@SuppressWarnings("unchecked")
//	BlockOffChain save(BlockOffChain blockOffChain);

}
