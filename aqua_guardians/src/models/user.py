
class User:
    def __init__(self, id, name, email, password, role):
        self.id = id
        self.name = name
        self.email = email
        self.password = password
        self.role = role
        
    def serialize(self):
        return {
            'id': self.id,
            'name': self.name,
            'email': self.email,
            'role': self.role
        }
        
    def login(email, password):
        
        return None

    def register(name, email, password, role):
        user = User(name=name, email=email, password=password, role=role)
        return user
