package BC_Hospital.Project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import BC_Hospital.Project.Model.BlockOffChain;

@Repository
public interface BlockOffChainRepository extends CrudRepository<BlockOffChain, String>{
	
	Optional<BlockOffChain>  findByhashfile(String hash);
	List<BlockOffChain> findAll();
	
	@SuppressWarnings("unchecked")
	BlockOffChain save(BlockOffChain blockOffChain);	

}
