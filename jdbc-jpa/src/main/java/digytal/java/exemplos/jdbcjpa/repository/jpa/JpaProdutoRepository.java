package digytal.java.exemplos.jdbcjpa.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import digytal.java.exemplos.jdbcjpa.model.cadastro.Produto;
import digytal.java.exemplos.jdbcjpa.repository.Repository;

public class JpaProdutoRepository implements Repository<Produto> {
	private EntityManager entityManager;

	public JpaProdutoRepository() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("MY_PU");
		entityManager = factory.createEntityManager();
	}

	public void insert(Produto e) {
		entityManager.getTransaction().begin();
		entityManager.persist(e);
		entityManager.getTransaction().commit();

	}

	public void update(Produto e) {
		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.getTransaction().commit();

	}

	public Produto select(Integer id) {
		return entityManager.find(Produto.class,id);
	}

	public List<Produto> selectAll() {
		Query query = entityManager.createQuery("SELECT e FROM Produto e"); //JPQL
		return query.getResultList();
	}
	
	public void fechar() {
		entityManager.clear();
		entityManager.close();
	}

}
