from uber_rides.session import Session
from uber_rides.client import UberRidesClient
import requests

jhaSe=input("jha se= ")
jhaTak=input("jha tak= ")
googleMapApiKey='AIzaSyAtx_lIJ0GsFLKtlaCsMyo7K7Rq8IeTCx4'
googleMapApiResponse=requests.get('https://maps.googleapis.com/maps/api/geocode/json?address={0}&key={1}'.format(jhaSe,googleMapApiKey))
api_json = googleMapApiResponse.json()

lat_jhase=api_json['results'][0]['geometry']['location']['lat']
lng_jhase=api_json['results'][0]['geometry']['location']['lng']
#print('lat= '+str(lat_jhase)+"  "+'lng= '+str(lng_jhase))
if(api_json['status']=='OK'):
	
		googleMapApiResponse_jhatak=requests.get('https://maps.googleapis.com/maps/api/geocode/json?address='+jhaTak+'&key='+googleMapApiKey)
		api_json_jhatak=googleMapApiResponse_jhatak.json()
		if(api_json_jhatak['status']=='OK'):
			lat_jhatak=api_json_jhatak['results'][0]['geometry']['location']['lat']
			lng_jhatak=api_json_jhatak['results'][0]['geometry']['location']['lng']
			#print('lat= '+str(lat_jhatak)+"  "+'lng= '+str(lng_jhatak))

		else:
			print("no cab available here")

else:
	print('no cab here ')
serverToken='HAt9gID7pnvbLmWxn0cpd_RRjGXOWuJOqWwymqgq'
#clientScret='lEU8tHxev2k-gvRAY9X7YU5cG3DSpc8p'

session=Session(server_token=serverToken)
client=UberRidesClient(session)


'''response=client.get_products(37.77, -122.41)
products=response.json.get('products')

print(products)

'''
response=client.get_price_estimates( start_latitude=lat_jhase,
    start_longitude=lng_jhase,
    end_latitude=lat_jhatak,
    end_longitude=lng_jhatak)
dekhle=response.json.get('prices')
#distance=estimate[0].get('distance')
#print(dekhle)
print('distance='+str(dekhle[0].get('distance'))+' km')
print('time='+str(dekhle[0].get('duration')/60)+' minutes')
print('estimated price='+dekhle[0].get('estimate'))
	#print(x+"aya")
