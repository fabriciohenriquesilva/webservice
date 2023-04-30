package dev.fabriciosilva.webservice.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Item WHERE fk_nota_fiscal = :notaFiscalId AND id = :itemId")
    boolean itemPertenceANotaFiscal(Long notaFiscalId, Long itemId);

    @Query("DELETE FROM Item WHERE fk_nota_fiscal = :notaFiscalId AND id = :itemId")
    @Modifying
    void removerItemDaNotaFiscal(Long notaFiscalId, Long itemId);
}
