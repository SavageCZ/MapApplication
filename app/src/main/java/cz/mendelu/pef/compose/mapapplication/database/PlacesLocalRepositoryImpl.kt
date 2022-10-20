package cz.mendelu.pef.compose.mapapplication.database

import kotlinx.coroutines.flow.Flow

class PlacesLocalRepositoryImpl (private val placesDao: PlacesDao) : IPlacesLocalRepository {

    override fun getAll(): Flow<List<Place>> {
        return placesDao.getAll()
    }

    override suspend fun insert(places: List<Place>) {
        placesDao.insert(places)
    }
}
