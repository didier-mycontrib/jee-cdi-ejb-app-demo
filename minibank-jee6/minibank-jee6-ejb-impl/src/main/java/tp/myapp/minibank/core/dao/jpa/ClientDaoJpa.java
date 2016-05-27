package tp.myapp.minibank.core.dao.jpa;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.mycontrib.generic.persistence.GenericDaoJpaImpl;

import tp.myapp.minibank.core.dao.ClientDao;
import tp.myapp.minibank.core.entity._Client;

@Stateless
@Local
public class ClientDaoJpa  extends GenericDaoJpaImpl<_Client,Long> implements ClientDao {

}
