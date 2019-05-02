package org.thallo.authenserver.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DelegateClientDetailService implements ClientDetailsService {
    private Logger logger = LoggerFactory.getLogger(DelegateClientDetailService.class);

    private Set<ClientDetailsService> detailsServiceList;
    private final static Set<Class<? extends  ClientDetailsService>> disabledClientDetailsService = new HashSet<>();

    public DelegateClientDetailService(Set<ClientDetailsService> detailsServiceList) {
        this.detailsServiceList = detailsServiceList;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetails details = null;

        Iterator<ClientDetailsService> iter = detailsServiceList.iterator();
        while(iter.hasNext()) {
            ClientDetailsService service = iter.next();
            if (disabledClientDetailsService.contains(service.getClass())) {
                continue;
            }
            try {
                details = service.loadClientByClientId(clientId);
            } catch (NoSuchClientException e) {
                logger.debug("{} 中没有找到client {}. ", service, clientId, e );
            }
            if (details != null) {
                break;
            }
        }

        return details;
    }

    public void addDisabledClientDetailService(Class<? extends ClientDetailsService> ...disabledClasses) {
        if (disabledClasses == null || disabledClasses.length == 0) {
            return;
        }
        disabledClientDetailsService.addAll(Arrays.asList(disabledClasses));
    }

    public static Set<Class<? extends ClientDetailsService>> getDisabledClientDetailsService() {
        return disabledClientDetailsService;
    }
}
