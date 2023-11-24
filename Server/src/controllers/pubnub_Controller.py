from pubnub.callbacks import SubscribeCallback
from pubnub.enums import PNStatusCategory, PNOperationType
from ..functions import pnb, channel


class MySubscribeCallback(SubscribeCallback):
    def message(self, pubnub, event):
        print("[MESSAGE received]")
        print("{}: {}".format(event.message["entry"], event.message["update"]))

    def presence(self, pubnub, event):
        print("[PRESENCE: {}]".format(event.event))
        print("uuid: {}, channel: {}".format(event.uuid, event.channel))

    def status(self, pubnub, event):
        if event.category == PNStatusCategory.PNConnectedCategory:
            print("[STATUS: PNConnectedCategory]")
            print("connected to channels: {}".format(event.affected_channels))


def activePubnub():
    pnb.add_listener(MySubscribeCallback())
    pnb.subscribe().channels(channel).with_presence().execute()