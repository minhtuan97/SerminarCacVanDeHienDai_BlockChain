package BC_Hospital.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OffchainRepository extends JpaRepository<Byte[], String> {

	@Query("SELECT file FROM offchain WHERE hash= :hash")
	Byte[] getFileFromHash(@Param("hash") String hashValue);
}
