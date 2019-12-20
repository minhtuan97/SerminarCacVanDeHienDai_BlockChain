package BC_Hospital.Project.Model;

//import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BlockOnChainRepository extends CrudRepository<BlockOnChain, Long>{

	// Lấy một record off chain thông qua mã hash của nó
	BlockOnChain findOnebyhash(String hash);
	
	@SuppressWarnings("unchecked")
	BlockOnChain save(BlockOnChain blockOnChain);
	
}
