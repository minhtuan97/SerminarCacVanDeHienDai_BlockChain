package BC_Hospital.Project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import BC_Hospital.Project.Model.BlockOnChain;

@Repository
public interface BlockOnChainRepository extends CrudRepository<BlockOnChain, String> {

	// Lấy một record off chain thông qua mã hash của nó
	BlockOnChain findByHash(String hash);
	
//	@SuppressWarnings("unchecked")
//	BlockOnChain save(BlockOnChain blockOnChain);

	
}
