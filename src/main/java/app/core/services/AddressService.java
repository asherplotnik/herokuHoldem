package app.core.services;

import app.core.entities.Player;
import app.core.exceptions.HoldemException;
import app.core.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AddressService {

    @Autowired
    PlayerRepository playerRepository;

    public boolean approveIpAddress(String ipAddress, int id) throws HoldemException {
        try{
            Optional<Player> player = playerRepository.findByIpAddress(ipAddress);
            if (player.isPresent() && id != player.get().getId()){
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new HoldemException(e.getLocalizedMessage());
        }
    }
}
