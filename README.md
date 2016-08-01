# Nearby Places
Small Java project to find a nearby place when given an IP.

Uses the freegeoip.net API to geolocate an IP for lat/long coordinates, and passes those on to the geonames.org API to find a nearby location

Before running, make sure to move or copy config/config.properties.default to config/config.properties and update it with a valid http://www.geonames.org username

If running as a jar, it'll need to have a config/config.properties directory and file in its relative path (for now)
It will also need the library org.apache.commons.validator.* in lib/ in its relative path (also for now, learning about ant build paths)
