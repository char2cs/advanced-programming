package mateourrutia.Imp.FileWriter;

import mateourrutia.Domain.Client;
import mateourrutia.Imp.ClientImp;
import mateourrutia.utils.persistance.FileWriter;

public class ClientFile extends ClientImp<FileWriter<Client>> {
    @Override
    protected FileWriter<Client> getClientImp() {
        return new FileWriter<>( Client.class.getSimpleName() );
    }
}
