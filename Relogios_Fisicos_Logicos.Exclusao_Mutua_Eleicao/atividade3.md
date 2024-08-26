# Pesquisa com implementação dos conceitos: Relógios Físicos e Lógicos. Exclusão Mútua e Eleição
## Pesquisa de forma livre os conceitos acima, apresentando exemplos em no mínimo duas linguagens.

### Relógios Físicos e Lógicos:
* Relógios Físicos:
    * Relógios físicos são os relógios reais dos computadores em um sistema distribuído. Eles medem o tempo real e são usados para registrar eventos. No entanto, devido a diferenças nas configurações dos sistemas e a drift dos relógios, os relógios físicos podem não estar sincronizados.

* Relógios Lógicos:
    * Relógios lógicos são uma forma de criar uma ordenação de eventos em sistemas distribuídos sem depender de relógios físicos. Um exemplo clássico é o Algoritmo de Lamport, que usa uma contagem de eventos para ordenar ações em sistemas distribuídos.

### Exemplos
Exemplos do algoritimo de Laport de relógios Lógicos
#### Python
    class LamportClock:
        def __init__(self):
            self.time = 0

        def tick(self):
            self.time += 1

        def update(self, other_time):
            self.time = max(self.time, other_time) + 1

        def __str__(self):
            return str(self.time)

    # Exemplo de uso
    clock1 = LamportClock()
    clock2 = LamportClock()

    clock1.tick()  # Evento no processo 1
    print(f"Clock1: {clock1}")

    clock2.tick()  # Evento no processo 2
    clock2.update(clock1.time)  # Sincroniza com clock1
    print(f"Clock2: {clock2}")

    clock1.tick()  # Outro evento no processo 1
    print(f"Clock1: {clock1}")

#### Java

    public class LamportClock {
        private int time;

        public LamportClock() {
            this.time = 0;
        }

        public void tick() {
            time++;
        }

        public void update(int otherTime) {
            time = Math.max(time, otherTime) + 1;
        }

        @Override
        public String toString() {
            return Integer.toString(time);
        }

        public static void main(String[] args) {
            LamportClock clock1 = new LamportClock();
            LamportClock clock2 = new LamportClock();

            clock1.tick();  // Evento no processo 1
            System.out.println("Clock1: " + clock1);

            clock2.tick();  // Evento no processo 2
            clock2.update(clock1.time);  // Sincroniza com clock1
            System.out.println("Clock2: " + clock2);

            clock1.tick();  // Outro evento no processo 1
            System.out.println("Clock1: " + clock1);
        }
    }

### Exclsão Mútua e Eleição
* Exclusão mútua é um mecanismo para garantir que apenas um processo pode acessar um recurso compartilhado ao mesmo tempo. Isso é crucial para evitar condições de corrida em sistemas distribuídos.

* Um exemplo de exclusão mútua é o Algoritmo de Ricart-Agrawala, que usa solicitações e respostas para garantir que apenas um processo tenha acesso à seção crítica por vez.

#### Exemplo python
    import threading
    import time

    class Process:
        def __init__(self, id, num_processes):
            self.id = id
            self.num_processes = num_processes
            self.request_time = 0
            self.lock = threading.Lock()

        def request_critical_section(self):
            with self.lock:
                self.request_time += 1
                print(f"Process {self.id} requesting critical section at time {self.request_time}")
                time.sleep(1)  # Simula tempo para enviar solicitações a outros processos

        def enter_critical_section(self):
            print(f"Process {self.id} entering critical section")

        def exit_critical_section(self):
            print(f"Process {self.id} exiting critical section")

    # Exemplo de uso
    p1 = Process(1, 2)
    p2 = Process(2, 2)

    p1.request_critical_section()
    p1.enter_critical_section()
    time.sleep(2)
    p1.exit_critical_section()

#### Java
    import java.util.concurrent.locks.ReentrantLock;

    public class Process {
        private final int id;
        private final int numProcesses;
        private int requestTime;
        private final ReentrantLock lock;

        public Process(int id, int numProcesses) {
            this.id = id;
            this.numProcesses = numProcesses;
            this.requestTime = 0;
            this.lock = new ReentrantLock();
        }

        public void requestCriticalSection() {
            lock.lock();
            requestTime++;
            System.out.println("Process " + id + " requesting critical section at time " + requestTime);
            try {
                // Simula tempo para enviar solicitações a outros processos
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }

        public void enterCriticalSection() {
            System.out.println("Process " + id + " entering critical section");
        }

        public void exitCriticalSection() {
            System.out.println("Process " + id + " exiting critical section");
        }

        public static void main(String[] args) {
            Process p1 = new Process(1, 2);
            Process p2 = new Process(2, 2);

            p1.requestCriticalSection();
            p1.enterCriticalSection();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            p1.exitCriticalSection();
        }
    }

* O algoritmo de eleição é usado para selecionar um líder entre vários processos em um sistema distribuído. O líder é um processo responsável por coordenar certas atividades ou tomar decisões que afetam o sistema como um todo.

#### Exemplo python
    class Process:
        def __init__(self, id, num_processes):
            self.id = id
            self.num_processes = num_processes
            self.is_leader = False

        def start_election(self):
            print(f"Process {self.id} starting election.")
            higher_ids = [i for i in range(self.id + 1, self.num_processes)]
            if higher_ids:
                print(f"Process {self.id} sending election messages to higher IDs {higher_ids}")
            else:
                print(f"Process {self.id} is the leader now!")
                self.is_leader = True

    # Exemplo de uso
    p1 = Process(1, 5)
    p2 = Process(2, 5)

    p1.start_election()

#### Java
    public class Process {
        private final int id;
        private final int numProcesses;
        private boolean isLeader;

        public Process(int id, int numProcesses) {
            this.id = id;
            this.numProcesses = numProcesses;
            this.isLeader = false;
        }

        public void startElection() {
            System.out.println("Process " + id + " starting election.");
            boolean higherIdExists = false;
            for (int i = id + 1; i < numProcesses; i++) {
                higherIdExists = true;
                System.out.println("Process " + id + " sending election message to higher ID " + i);
            }
            if (!higherIdExists) {
                System.out.println("Process " + id + " is the leader now!");
                isLeader = true;
            }
        }

        public static void main(String[] args) {
            Process p1 = new Process(1, 5);
            Process p2 = new Process(2, 5);

            p1.startElection();
        }
    }
