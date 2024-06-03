package mateourrutia.Imp.StringWriter;

import mateourrutia.Domain.Client;
import mateourrutia.Imp.ClientImp;
import mateourrutia.utils.persistance.StringWriter;

public class ClientString extends ClientImp<StringWriter<Client>> {
    @Override
    protected StringWriter<Client> getClientImp() {
        return new StringWriter<>( Client.class.getSimpleName() );
    }
}
