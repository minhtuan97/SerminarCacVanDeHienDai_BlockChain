package BC_Hospital.Project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import BC_Hospital.Project.Model.BlockOnChain;

@Repository
public interface BlockOnChainRepository extends CrudRepository<BlockOnChain, String> {

	// Lấy một record off chain thông qua mã hash của nó
	Optional<BlockOnChain> findByhash(String hash);
	
	@SuppressWarnings("unchecked")
	BlockOnChain save(BlockOnChain blockOnChain);
	
	//List<BlockOnChain> findTop1ByOrderBytimestampDesc();
	List<BlockOnChain> findTop1ByOrderByTimestampDesc();

	
}
