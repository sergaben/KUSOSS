package filters

import javax.inject.Inject

import play.api.http.{DefaultHttpFilters, EnabledFilters}
import play.filters.cors.CORSFilter
class Filters @Inject()(enabledFilters: EnabledFilters, corsFilter: CORSFilter, log: LoggingFilter)
  extends DefaultHttpFilters(enabledFilters.filters :+ corsFilter: _*)
