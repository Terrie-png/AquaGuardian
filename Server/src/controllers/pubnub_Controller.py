import time
from unittest import result
from pubnub.callbacks import SubscribeCallback
from pubnub.enums import PNStatusCategory, PNOperationType
from ..functions import pnb, channel, mysql

class MySubscribeCallback(SubscribeCallback):
    def message(self, pubnub, event):
        print(event.message)
        timestamp = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(event.timestamp/1000))
        data = event.message
        if "ph_data" in data.keys() and "tds_data" in data.keys() and "turb_data" in data.keys():
            ph_data = data['ph_data']
            tds_data = data['tds_data']
            turb_data = data['turb_data']
            cursor = mysql.connection.cursor()
            query = "INSERT INTO sensorData (timestamp,ph_analog_in,ph_voltage_in, tds_analog_in,tds_voltage_in,turb_analog_in,turb_voltage_in) VALUES (%s, %s, %s, %s, %s, %s, %s,)"
            values = (timestamp, ph_data['analog_in'], ph_data['voltage_in'], tds_data['analog_in'], tds_data['voltage_in'], turb_data['analog_in'], turb_data['voltage_in'],)
            cursor.execute(query, values)
            mysql.connection.commit()
            cursor.close()
        else:
            print("Data is not complete")
        

    def presence(self, pubnub, event):
        print(event.message)
        timestamp = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(event.timestamp/1000))
        data = event.message
        if "ph_data" in data.keys() and "tds_data" in data.keys() and "turb_data" in data.keys():
            ph_data = data['ph_data']
            tds_data = data['tds_data']
            turb_data = data['turb_data']
            cursor = mysql.connection.cursor()
            query = "INSERT INTO sensorData (timestamp,ph_analog_in,ph_voltage_in, tds_analog_in,tds_voltage_in,turb_analog_in,turb_voltage_in) VALUES (%s, %s, %s, %s, %s, %s, %s,)"
            values = (timestamp, ph_data['analog_in'], ph_data['voltage_in'], tds_data['analog_in'], tds_data['voltage_in'], turb_data['analog_in'], turb_data['voltage_in'],)
            cursor.execute(query, values)
            mysql.connection.commit()
            cursor.close()
        else:
            print("Data is not complete")
        
        
    def status(self, pubnub, event):
        if event.category == PNStatusCategory.PNConnectedCategory:
            print("[STATUS: PNConnectedCategory]")
            print("connected to channels: {}".format(event.affected_channels))


def activePubnub():
    pnb.add_listener(MySubscribeCallback())
    pnb.subscribe().channels(channel).execute()

    
def getSensorData():
    cursor = mysql.connection.cursor()
    query = "SELECT * FROM sensorData ORDER BY timestamp DESC LIMIT 1"
    cursor.execute(query)
    result = cursor.fetchone()
    cursor.close()
    return result


