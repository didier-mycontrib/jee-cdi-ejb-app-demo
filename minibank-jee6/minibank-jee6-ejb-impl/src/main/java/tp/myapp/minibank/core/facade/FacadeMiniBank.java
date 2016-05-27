package tp.myapp.minibank.core.facade;

import tp.myapp.minibank.core.service.GestionClients;
import tp.myapp.minibank.core.service.GestionComptes;

public interface FacadeMiniBank {
    public GestionComptes getServiceGestionComptes();
    public GestionClients getServiceGestionClients();
}
