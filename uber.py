from uber_rides.session import Session
from uber_rides.client import UberRidesClient

serverToken='I799gsdbtW7YqbPSz5y7Tw_5exla3yjBpYmqsQ5L'
#clientScret='lEU8tHxev2k-gvRAY9X7YU5cG3DSpc8p'

session=Session(server_token=serverToken)
client=UberRidesClient(session)


'''response=client.get_products(37.77, -122.41)
products=response.json.get('products')

print(products)

'''
response=client.get_price_estimates( start_latitude=30.733315,
    start_longitude=76.779418,
    end_latitude=30.694209,
    end_longitude=76.860565)
dekhle=response.json.get('prices')
#distance=estimate[0].get('distance')
#print(dekhle)
print('distance='+str(dekhle[0].get('distance'))+' km')
print('time='+str(dekhle[0].get('duration')/60)+' minutes')
print('estimated price='+dekhle[0].get('estimate'))
	#print(x+"aya")
