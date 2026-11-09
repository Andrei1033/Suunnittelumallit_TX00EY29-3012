# Kubernetes manifests (H2 embedded)

These manifests run the JavaFX application as one pod and mount a persistent
volume for its embedded H2 database. There is intentionally no database
Deployment or database Service: H2 runs inside the application process.

## Before applying

1. Build and push the Docker image using your existing Dockerfile/Jenkins pipeline.
2. Edit `deployment.yaml` and replace
   `YOUR_DOCKERHUB_USERNAME/temperature-converter:latest` with the exact image
   name and tag you pushed.
3. Start Minikube: `minikube start`.
4. Start Xming or VcXsrv on the host and allow connections from Minikube.
   The manifest sets `DISPLAY=host.minikube.internal:0.0`. Depending on your
   host/Minikube driver and X-server access-control settings, this address may
   need adjustment. A pod being Running does not by itself prove that the
   JavaFX window can connect to the host X server.

## Apply and verify

From the project root:

```bash
kubectl apply -f k8s/pvc.yaml
kubectl apply -f k8s/deployment.yaml
kubectl get pvc
kubectl get pods
kubectl describe pod -l app=temperature-converter
kubectl logs deployment/temperature-converter
```

To watch startup:

```bash
kubectl get pods -w
```

To remove the application Deployment:

```bash
kubectl delete -f k8s/deployment.yaml
```

To remove the claim as well (this also deletes the persistent database volume
and its data, depending on the Minikube storage provisioner):

```bash
kubectl delete -f k8s/pvc.yaml
```

## Important project configuration note

`DBConnection` reads `DB_URL`, `DB_USER`, and `DB_PASSWORD` environment variables
before its properties/default values. The Deployment sets these to use
`jdbc:h2:file:/data/temperature_db`, so it does not rely on the local
`database.properties` file and does not connect to MariaDB.

The H2 database is created/initialized by the application when it starts.
