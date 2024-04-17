provider "google" {
  credentials = "${file("/home/serge_hakobyan1/.config/gcloud/application_default_credentials.json")}"
  project     = var.project
  region      = var.region
  zone        = var.zone
}



resource "google_compute_instance" "vm_instance" {

  ## for a setup having multiple instances of the same type, you can do
  ## the following, there would be 2 instances of the same configuration
  ## provisioned
  count        = 1
  name         = "${var.instance-name}-${count.index}"

  machine_type = "e2-small"

  boot_disk {
    initialize_params {
      image = "ubuntu-2004-focal-v20240307b"
    }
  }

  network_interface {
    # A default network is created for all GCP projects
    network       = "default"
    access_config {
    }
  }
# On ajoute notre clé publique dans la liste des hôtes acceptés sur la machine
  metadata = {
    # ======================================
    #
    # Ajoutez ici votre clé publique (cf : ./README) de la manière suivante
    # dev:<VOTRE_CLE> dev
    #
    # ======================================
    "ssh-keys" = <<EOT
      dev:ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQDUK/blYTuABgu46bxEzPRAyy8dZUtMFeeGO0WOKKVUCfwcvVAYIJBQ7OGRIInx1oSMg8mHkaTtPAroeR9xod+lkQXznUIhIx3uj4L8KlecnnAK2AzmtR7OQsEjvyuOEFA2DkAcGPDUSPHkOn9S55Eavc948AJ7PekMSQV6UZCV5o/XW8AdanMwpbKXumd7nkawO/pgTExuulU+rJBtZbXTn14zgcrWOx4njfzhBIEJAIBeVvslVDvuJ4Q9ZaA5OEbY2gFCchwKr5MtCORClbx6EUwY6aW+yGKi2iXEDNh4TvB2OIF/GFUdh3B3X3lHISOsEVfpTo6HbFo6pdtdS6kQPP7xY/5VfnfoT3O7Klq0UEOOc6Q+MgYvMBw45lcqdqyTW4FS3yh0gUCDfzbyg23YOEeQmblU5x3ia0E+8Vcmg9DndU0S96FWMe8AXFZcsQE7goW99JiLaFnswJ78244X6yw+5PdOUGWI0J0qWnknUo7Sy7g1s1ANzfXGiP1GaDpFdt0x/2mFGkVmcUY89rZhDbHERVcszlhny8JLCkneCLk0RrL2uZYNnlIj1DWX11n1GQE9/m1Iwa3E0gYHPFbZYpjgkuuciTeFgQIS/dK6mRilaRXhKQNDeo8xmIv3fPukZxOW1ZpBipcS/Xxk2qwnukT9pFOwtTxf9Fmuxav8MQ== dev
      EOT
  }

  # On commence ensuite à executer du code sur la VM
  provisioner "remote-exec" {
    # On fait les mise à jours classiques et on installe python et autres outillages
    inline = [
	"sudo apt update",
	"sudo apt install -y software-properties-common",
	"sudo apt-get -y install python3-pip",
	"pip install docker",
	"pip install --upgrade ansible; pip show docker",
    ]

    # On utilise la clé ssh pour se connecter, elle utilise la clé privée que vous avez en local dans ~/.shh/
    connection {
      type        = "ssh"
      # L'utilisateur dev est défini grâce a la clé ssh fourni dans les metadata
      user        = "dev"
      # Adresse IP de la machine
      host        = self.network_interface[0].access_config[0].nat_ip
      private_key = "${file("/home/serge_hakobyan1/.ssh/id_rsa")}"
    }
  }

  # Ensuite on exécute depuis un fichier local une commande pour modifier la VM
  # Pour cela, on utilise ansible et une connexion ssh avec la même clé privée que la dernière fois
  provisioner "local-exec" {
    command = "ansible-playbook -u dev -i ${self.network_interface.0.access_config.0.nat_ip}, ./ansible/main.yml"
  }
}

// A variable for extracting the external ip of the instance
output "ip" {
 value = "${google_compute_instance.vm_instance[0].network_interface.0.access_config.0.nat_ip}"
}
