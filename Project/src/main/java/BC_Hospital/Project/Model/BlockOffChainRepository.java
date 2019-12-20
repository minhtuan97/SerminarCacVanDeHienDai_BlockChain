package BC_Hospital.Project.Model;

//import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BlockOffChainRepository extends CrudRepository<BlockOffChain, Long>{
	
	// Lấy một record off chain thông qua mã hash của nó
	BlockOffChain findOneByHash(String hash);
	
	@SuppressWarnings("unchecked")
	BlockOffChain save(BlockOffChain blockOffChain);

}
