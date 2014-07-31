package ru.nemiroff;

/**
 * Created by nemiroff on 01.08.2014.
 */
public class FirmConfigFactory {

   public static FirmConfig getConfigFactory() {
       // While a simple creation XMLFirmConfig
       return new XMLFirmConfig();
   }

}
